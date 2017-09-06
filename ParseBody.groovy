import groovy.util.XmlSlurper;
import groovy.util.slurpersupport.*;

// create groovyUtils and XmlHolder for response of the request
def groovyUtils = new com.eviware.soapui.support.GroovyUtils(context);
def requestResponse = groovyUtils.expand('${ChangedSinceDate#Response}');
def holder = groovyUtils.getXmlHolder(requestResponse);
def parseResponse = new XmlSlurper().parseText(requestResponse);

// loop item nodes in response message

def mapConstruct(mapNodes) {
    mapNodes.children().collectEntries 
    { 
        [ 
        	it.name(), it.childNodes() ? mapConstruct(it) : it.text() 
        ] 
    }
}

def parseMaps(Map map, int counter = 0, org.apache.log4j.Logger log)
{
	map.each
	{
		k, v -> 
		// log.info("$k");
		if (v.getClass().toString() == "class java.util.LinkedHashMap")
		{
			// log.info("recurse case $v");
			parseMaps(v, counter++, log);
		}
		else
		{	
			// log.info("base case");
			if (v.size() > 0)
			{
				log.info("$k has a value of $v");
			}
		}
	}
}

def map = mapConstruct(parseResponse);

log.info(map.getClass());

String [] keySet = map.keySet().toArray();

log.info(keySet.size());

parseMaps(map["Body"], 0, log);
