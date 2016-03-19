$(document).ready(function () {
    //Loads Map
    $address = $('#address').text();
    $html = '<p style="font-size:smaller; font-weight:bold;">' + $address + '</p>';

    $("#map").goMap({
        markers: [{
                address: $address,
                title: 'Marker 1',
                html: {
                    content: $html,
                    popup: true
                }
            }],
        address: $address,
        zoom: 17,
        maptype: 'ROADMAP',
        scaleControl: true
    });


    //Fancy Box Plugin for Images
    $(document).ready(function () {
        $(".fancybox-button").fancybox({
            prevEffect: 'none',
            nextEffect: 'none',
            closeBtn: false,
            helpers: {
                title: {type: 'inside'},
                buttons: {}
            }
        });
    });
});