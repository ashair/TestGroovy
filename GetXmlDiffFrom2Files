import org.custommonkey.xmlunit.*;

XMLUnit.setIgnoreWhitespace(true)
XMLUnit.setIgnoreComments(true)
XMLUnit.setIgnoreDiffBetweenTextAndCDATA(false)
XMLUnit.setNormalizeWhitespace(true)

def request1Xml = context.expand( '${GetEnrichissement[8.2]#Response}' )
def request2Xml = context.expand( '${GetEnrichissement[9.7]#Response}' )


Diff diff = new Diff (request1Xml, request2Xml);
DetailedDiff myDiff = new DetailedDiff (diff);

//Create an object for GroovyUtils
def groovyUtils=new  com.eviware.soapui.support.GroovyUtils(context)
// Using the method projectPath you can get the project path which you are working
def projectPath = groovyUtils.projectPath
def fileOut = new File (projectPath+'/logOutput.log');
//Convert myDiff to a string to write to a file. It keeps the formating as far as I can tell. 
fileOut.write (myDiff.toString());
