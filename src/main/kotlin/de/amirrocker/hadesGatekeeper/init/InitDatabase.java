package de.amirrocker.hadesGatekeeper.init;

import de.amirrocker.hadesGatekeeper.domain.offer.OfferRepository;
import de.amirrocker.hadesGatekeeper.domain.product.Product;
import de.amirrocker.hadesGatekeeper.domain.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class InitDatabase {

    private final ProductRepository productRepository;
    private final OfferRepository offerRepository;

    public InitDatabase(ProductRepository productRepository, OfferRepository offerRepository) {
        this.productRepository = productRepository;
        this.offerRepository = offerRepository;
    }

    public void init() {
        Product standardCarInsurance = InitProductsBuilder.Companion.standardCarInsurance();
        productRepository.add(standardCarInsurance);

        Product productFromRepo = productRepository.withCode(standardCarInsurance.getCode());

        offerRepository.add(InitOffersBuilder.Companion.standardOneYearOCOfferValidUntil(
                productFromRepo,
                "OFF-001",
                LocalDate.now().plusDays(15)
        ));
        offerRepository.add(InitOffersBuilder.Companion.standardOneYearOCOfferValidUntil(
                productFromRepo,
                "OFF-002",
                LocalDate.now().minusDays(3)
        ));
    }

}
