$(document).ready(function() {
    // all custom jQuery will go here
    $("div").click(function() {alert("Hello, javascript!");});
  })

/*
        (function poll() {
           setTimeout(function() {
               $.ajax({ url: "server", success: function(data) {
                    alert();
                    sales.setValue(data.value);
               }, dataType: "json", complete: poll });
            }, 30000);
        })();
        poll();

        (function longpoll() {
           setTimeout(function() {
               $.ajax({ url: "server", success: function(data) {
                    sales.setValue(data.value);
               }, dataType: "json", complete: poll });
            }, 30000);
        })();
        longpoll()
*/