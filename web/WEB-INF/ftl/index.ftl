<html>
    <head>
        <title>Exchange rates</title>
        
        <#include "styles.ftl">
        <#include "imports.ftl">
        
        <script>
            $(function() {
                $( "#exchangeRatesDate" ).datepicker({dateFormat: 'yy.mm.dd'});
            });
        </script>
    </head>

    <body>
        <#include "navigation.ftl">
        
        <div id="content">
            <#if errorMessage != ""><div id="errorMessage">${errorMessage}</div><br></#if>
            
            <fieldset>
                <form action="showExchangeRatesByDate.html" method="post">
                    Enter currencies date:
                    <input type="text" id="exchangeRatesDate" name="exchangeRatesDate" maxlength="10" value="${exchangeRatesDate}" />
                    <input type="submit" name="exchangeRatesButton" value="Get exchange rates" />
                </form>
            </fieldset>
            
            <br/>
            <table class="datatable">
                <tr>
                    <th>Currency</th>
                    <th>Quantity</th>
                    <th>Unit</th>
                    <th>Rate</th>
                    <th>Previous rate</th>
                    <th>Change, %</th>
                </tr>
    
                <#list model["exchangeRates"] as exchangeRate>
                <tr>
                    <td>${exchangeRate.currency}</td>
                    <td>${exchangeRate.quantity}</td>
                    <td>${exchangeRate.unit}</td>
                    <td>${exchangeRate.rate}</td>
                    <td>${exchangeRate.previousRate}</td>
                    <td>${exchangeRate.change}</td>
                </tr>
                </#list>
            </table>
        </div>  
    </body>
</html>  