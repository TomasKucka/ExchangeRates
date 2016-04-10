<html>
    <head>
        <title>Exchange rates chart</title>        
        
        <#include "styles.ftl">
        <#include "imports.ftl">
        
        <script>
            $(function() {
                $( "#exchangeRatesDateLow" ).datepicker({dateFormat: 'yy.mm.dd'});
            });
            $(function() {
                $( "#exchangeRatesDateHigh" ).datepicker({dateFormat: 'yy.mm.dd'});
            });
        </script>
        
        <script type="text/javascript">
            <#if chartLoaded>
                google.charts.load('current', {'packages':['corechart']});
                google.charts.setOnLoadCallback(drawChart);
            </#if>

            function drawChart() {
                var array = [
                    ['Date', 'Rate']
                ];
                    
                <#list model["chart"] as item>
                    array.push(['${item.date}', ${item.rate}]);
                </#list>
                    
                var data = google.visualization.arrayToDataTable(array);

                var options = {
                    title: 'Currency chart',
                    hAxis: {title: 'Date',  titleTextStyle: {color: '#333'}},
                };

                var chart = new google.visualization.AreaChart(document.getElementById('chart'));
                chart.draw(data, options);
            }
        </script>
    </head>
    
    <body>
        <#include "navigation.ftl">
        
        <div id="content">
            <#if errorMessage != ""><div id="errorMessage">${errorMessage}</div><br></#if>
            
            <fieldset>
                <form action="showChartByCurrencieAndPeriod.html" method="post">
                    Choose currency:
                    <select name="currency">
                    <#list model["currencies"] as currency>
                        <option value="${currency.currency}" <#if currency.currency == selectedCurrency>selected</#if>>${currency.description}</option>
                    </#list>
                    </select>

                    <br><br>
                    Enter currencies date from:
                    <input type="text" id="exchangeRatesDateLow" name="exchangeRatesDateLow" maxlength="10" value="${exchangeRatesDateLow}" />
                    to:
                    <input type="text" id="exchangeRatesDateHigh" name="exchangeRatesDateHigh" maxlength="10" value="${exchangeRatesDateHigh}" />
                    <input type="submit" name="exchangeRatesButton" value="Draw currency chart" />
                </form>
            </fieldset>
            
            <#if chartLoaded>
                <div id="chart" style="width: 100%; height: 500px;"></div>
            
                <br/>
                <table class="datatable">
                    <tr>
                        <th>Date</th>
                        <th>Rate</th>
                    </tr>

                    <#list model["chart"] as item>
                    <tr>
                        <td>${item.date}</td>
                        <td>${item.rate}</td>
                    </tr>
                    </#list>
                </table>
            </#if>
        </div>  
    </body>
</html>  