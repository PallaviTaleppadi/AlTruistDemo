--Web UI automation isng Selenium, Java, TestNg--

 Retrieve Data from UI and compare it with an existing collection (below given test data)
Given Test Data : List<String> expectedStockSymbols = Arrays.asList("NFLX","MSFT","TSLA".......)

Steps:
1. Opens a webpage www.google.com/finance on a chrome browser
2. Verifies the page is loaded by asserting the page title
3. Retrieves the stock symbols listed under the section “You may be interested in info”
4. Compare the stock symbols retrieved from (3) with expectedStockSymbols
5. Print all stock symbols that are in (3) but not in expectedStockSymbols
6. Print all stock symbols that are in expectedStockSymbols but not in (3)
