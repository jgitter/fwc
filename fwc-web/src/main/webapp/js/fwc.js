(function() {
    var fwc = {};

    fwc.startup = function() {
        $(document).foundation();
        resetDataDiv();
        $(window).on('resize', function() {
            resetDataDiv();
        });

        $('.ejb-rest').click(fwc.callService);
        $('.spring-rest').click(fwc.callService);
        $('.ejb-soap').click(fwc.callService);
        $('.spring-soap').click(fwc.callService);
    };

    fwc.callService = function(evt) {
        $('.wait').show();
        $('.data').html('');
        var method = evt.currentTarget.classList.item(0);
        fetchData(method);
    };

    var fetchData = function(method) {
        var keyword = $('.keyword').val();
        if (keyword == '') {
            handleRestData("Keyword is required.");
            return false;
        }

        if (method == 'ejb-rest') {
            $.ajax('/fwc-ejb/rest/institution/' + keyword, {
                success : handleRestData,
                error : handleError
            });
        } else if (method == 'spring-rest') {
            $.ajax('/fwc-spring/rest/institution/' + keyword, {
                success : handleRestData,
                error : handleError
            });
        } else if (method == 'ejb-soap') {
            $.soap({
                url : '/fwc-ejb/soap/institution/InstitutionService',
                appendMethodToURL : false,
                SOAPAction : '',
                data : function(Soap) {
                    return new Soap('soap:Envelope')
                        .addNamespace('soap', 'http://schemas.xmlsoap.org/soap/envelope')
                        .newChild('soap:Header').end()
                        .newChild('soap:Body')
                            .newChild('fwc:findInstitutions')
                                .addNamespace('fwc', 'http://fwc.gitter.org/services/')
                                .newChild('keyword').val(keyword).end()
                            .end()
                        .end();
                },
                success : handleSoapData,
                error : handleError
            });
        } else if (method == 'spring-soap') {
            $.soap({
                url : '/fwc-spring/soap/institution/InstitutionService',
                appendMethodToURL : false,
                SOAPAction : '',
                data : function(Soap) {
                    return new Soap('soap:Envelope')
                        .addNamespace('soap', 'http://schemas.xmlsoap.org/soap/envelope')
                        .newChild('soap:Header').end()
                        .newChild('soap:Body')
                            .newChild('fwc:findInstitutionsRequest')
                                .addNamespace('fwc', 'http://fwc.gitter.org/services/')
                                .newChild('fwc:keyword').val(keyword).end()
                            .end()
                        .end();
                },
                success : handleSoapData,
                error : handleError
            });
        }
    };

    var handleRestData = function(data, status, xhr) {
        $('.data').html(vkbeautify.json(data, 2));
        $('.wait').hide();
        resetDataDiv();
    }

    var handleSoapData = function(data, status, xhr) {
        console.log(data);
        console.log(data.toString());
        $('.data').html(htmlEncode(vkbeautify.xml(data.toString(), 2)));
        $('.wait').hide();
        resetDataDiv();
    }

    var handleError = function(xhr, status, error) {
        $('.data').html(xhr.httpCode + " : " + xhr.httpText);
        $('.wait').hide();
        resetDataDiv();
    }

    var resetDataDiv = function() {
        $('.response').height($('body').height() - $('.form').height() - 40);
    }

    var htmlEncode = function(value) {
        // create a in-memory div, set it's inner text (which jQuery
        // automatically encodes) then grab the encoded contents back out.
        // The div never exists on the page.
        return $('<div/>').text(value).html();
    }

    window.fwc = fwc;
})();