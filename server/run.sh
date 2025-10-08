cd ShopEurekaServer

mvn springboot:run -DSkipTests

cd ..

cd ConfigurationServer
mvn springboot:run -DSkipTests

cd ..

cd ShopGateway
mvn springboot:run -DSkipTests

cd ..

cd Purchases
mvn springboot:run -DSkipTests

cd ..

cd Catalog
ls
mvn springboot:run -DSkipTests

cd ..

cd Payment
mvn springboot:run -DSkipTests

cd ..

cd usermanager
mvn springboot:run -DSkipTests

cd ..

cd zipkin
mvn springboot:run -DSkipTests

cd ..

# -----------
