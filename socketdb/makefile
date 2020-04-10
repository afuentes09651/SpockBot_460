# make file for creating and starting socket database
create:
	java -jar ../mckoi1.0.3/mckoidb.jar -conf ./BedrockU.conf -create "admin" "9999"
start : 
	java -jar ../mckoi1.0.3/mckoidb.jar -conf BedrockU.conf
cleandb: 
	rm data/*
	rm log/*
