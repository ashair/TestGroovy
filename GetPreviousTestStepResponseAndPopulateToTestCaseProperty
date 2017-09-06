//Get current Test step Name
//def testStepName = context.getCurrentStep().getLabel();
def currentStepInd = context.currentStepIndex
def previousStepName = testRunner.testCase.getTestStepAt(currentStepInd - 3)
log.info (previousStepName.name);
def response = context.expand( '${{=previousStepName.name}${Response}' );
context.testCase.setPropertyValue("Response8.2", response);
