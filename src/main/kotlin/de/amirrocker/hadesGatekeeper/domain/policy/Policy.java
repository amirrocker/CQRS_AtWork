package de.amirrocker.hadesGatekeeper.domain.policy;

import de.amirrocker.hadesGatekeeper.domain.base.AggregateRoot;
import de.amirrocker.hadesGatekeeper.domain.base.Event;
import de.amirrocker.hadesGatekeeper.domain.common.BusinessException;
import de.amirrocker.hadesGatekeeper.domain.common.MonetaryAmount;
import de.amirrocker.hadesGatekeeper.domain.offer.CoverPrice;
import de.amirrocker.hadesGatekeeper.domain.offer.Offer;
import de.amirrocker.hadesGatekeeper.domain.policy.events.CoverageExtendedPolicyVersionCreated;
import de.amirrocker.hadesGatekeeper.domain.policy.events.InitialPolicyVersionCreated;
import de.amirrocker.hadesGatekeeper.domain.policy.events.PolicyEventsData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class Policy extends AggregateRoot {

    public   String number;

    private List<PolicyVersion> versions = valueOf();

    public PolicyVersions getPolicyVersions() {
        return newPolicyVersions();
    }

    public Policy(UUID policyId, List<Event> events) {
        setId(policyId);
        loadsFromHistory(events);
    }

    public static Policy convertOffer(Offer offer, LocalDate purchaseDate, LocalDate policyStartDate) {
        return new Policy(UUID.randomUUID(), offer, purchaseDate, policyStartDate);
    }

    private Policy(
            UUID policyId, Offer offer, LocalDate purchaseDate, LocalDate policyStartDate
    ) {
        if(offer.isConverted()) {
            throw new BusinessException("Offer already converted");
        }
        if(offer.isRejected()) {
            throw new BusinessException("Offer has already been rejected");
        }
        if(offer.isExpired(purchaseDate)) {
            throw new BusinessException("Offer has already expired");
        }
        if(offer.isExpired(policyStartDate)) {
            throw new BusinessException("Offer not valid at this policy start date");
        }

        String policyNumber = UUID.randomUUID().toString();
        DateRange coverPeriod = DateRange.Companion.between(policyStartDate, policyStartDate.plus(offer.getCoverPeriod()));
        List<PolicyCover> covers = offer.getCovers().parallelStream()
                .map(coverPrice -> PolicyCover.Companion.forPrice(coverPrice, coverPeriod))
                .collect(Collectors.toList());

        setId(policyId);

        Event<Policy> event = new InitialPolicyVersionCreated(
            policyNumber,
            offer.getProductCode(),
            coverPeriod,
            purchaseDate,
            offer.getCustomer(),
            offer.getCar(),
            covers
        );

        applyChange(event);


    }

    private PolicyVersions newPolicyVersions() {
        return new PolicyVersions(versions);
    }

    private ArrayList valueOf() {
        return new ArrayList<>();
    }

    public void extendCoverage(@NotNull LocalDate effectiveDateOfChange, @NotNull String newCoverCode, BigDecimal newCoverPrice, Period newCoverPriceUnit) {
        /* handle any existing preconditions - Obamacare :P */
        if(isTerminated()) {
            throw new BusinessException("Cannot extend terminated policy");
        }

        Optional<PolicyVersion> versionAtDateOptional = getPolicyVersions().effectiveAtDate(effectiveDateOfChange);
        if(versionAtDateOptional.isEmpty()) {
            throw new BusinessException("Policy at effective Date does not exist.");
        }

        PolicyVersion versionAtDate = versionAtDateOptional.get();
        if(!versionAtDate.coversDate(effectiveDateOfChange)) {
            throw new BusinessException("Policy does not cover the versionDate");
        }

        if(versionAtDate.containsCover(newCoverCode)) {
            throw new BusinessException("This cover already exists");
        }

        int newVersionNumber = versions.size() + 1;
        DateRange versionPeriod = DateRange.Companion.between(effectiveDateOfChange, versionAtDate.getCoverPeriod().getTo());
        CoverPrice newCover = new CoverPrice(newCoverCode, MonetaryAmount.Companion.from(newCoverPrice), newCoverPriceUnit);
        PolicyCover newCoverage = PolicyCover.Companion.forPrice(newCover, versionPeriod);

        final Event event = new CoverageExtendedPolicyVersionCreated(
                newVersionNumber,
                versionAtDate.getVersionNumber(),
                versionPeriod.getFrom(),
                versionPeriod.getTo(),
                newCoverage
        );

        applyChange(event);


//        Optional<PolicyVersion> versionAtDateOpt = getPolicyVersions().effectiveAtDate(effectiveDateOfChange);
//
//        Optional<PolicyVersion> versionAtEffectiveDate = getPolicyVersions().effectiveAtDate(effectiveDateOfChange);
//        if(!versionAtEffectiveDate.isPresent()) {
//            throw new BusinessException("No active version at given date");
//        }
//
//        PolicyVersion annexVersion = addNewVersionBasedOn(versionAtEffectiveDate.get(), effectiveDateOfChange);
//        annexVersion.addCover(newCoverCode, new UnitPrice(
//                MonetaryAmount.Companion.from("100"),
//                Period.of(2020, 1, 1)
//        ), annexVersion.getCoverPeriod());
    }

    private PolicyVersion addNewVersionBasedOn(PolicyVersion policyVersion, LocalDate effectiveDateOfChange) {
        return null;
    }

    private boolean isTerminated() {
        return versions.parallelStream()
                .anyMatch(policyVersion -> policyVersion.isActive() && PolicyStatus.Terminated.equals(policyVersion.getPolicyStatus()));
    }

    public void apply(InitialPolicyVersionCreated event) {
        number = event.getPolicyNumber();
        PolicyVersion newPolicyVersion = PolicyVersion.Companion.of(
    1,
                PolicyVersionStatus.Active,
                PolicyStatus.Active,
                DateRange.Companion.between(event.getCoverFrom(), event.getCoverTo()),
                DateRange.Companion.between(event.getCoverFrom(), event.getCoverTo()),
                event.getCovers().parallelStream()
                .map(policyCover -> PolicyCover.Companion.of(
                                            policyCover.getCoverCode(),
                                            policyCover.getCoverPeriod(),
                                            new UnitPrice(
                                                    policyCover.getPrice().getPrice(),
                                                    Period.of(2020, 1, 1)
                                            )
                                    )
                )
                .collect(Collectors.toList())
        );
        versions.add(newPolicyVersion);
    }

    public void apply(CoverageExtendedPolicyVersionCreated event) {
        final DateRange versionPeriod = DateRange.Companion.between(event.getVersionFrom(), event.getVersionTo());
        final PolicyVersion policyVersion = getPolicyVersions().withNumber(event.getBaseVersionNumber()).get();

        final PolicyVersion draft = policyVersion.createDraftCopy(event.getVersionNumber(), versionPeriod);
        final PolicyEventsData.PolicyCoverData newCover = event.getNewCover();
        draft.addCover(
                newCover.getCode(),
                new UnitPrice(newCover.getPrice(), newCover.getPriceUnit()),
                DateRange.Companion.between(newCover.getCoverFrom(), newCover.getCoverTo())
        );
        versions.add(draft);
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<PolicyVersion> getVersions() {
        return versions;
    }

    public void setVersions(List<PolicyVersion> versions) {
        this.versions = versions;
    }
}
