package org.gradiant.jenkins.slack

import hudson.tasks.test.AbstractTestResultAction
import hudson.model.Actionable

@NonCPS
String getTestSummary() {
  def testResultAction = currentBuild.rawBuild.getAction(AbstractTestResultAction.class)
  def summary = ""

  if (testResultAction != null) {
    def total = testResultAction.getTotalCount()
    def failed = testResultAction.getFailCount()
    def skipped = testResultAction.getSkipCount()

    summary = "Test results:\n\t"
    summary = summary + ("Passed: " + (total - failed - skipped))
    summary = summary + (", Failed: " + failed)
    summary = summary + (", Skipped: " + skipped)
  } else {
    summary = "No tests found"
  }
  return summary
}
@NonCPS
String getFailedTests() {
    def testResultAction = currentBuild.rawBuild.getAction(AbstractTestResultAction.class)
    def failedTestsString = "```"

    if (testResultAction != null) {
        def failedTests = testResultAction.getFailedTests()
        
        if (failedTests.size() > 9) {
            failedTests = failedTests.subList(0, 8)
        }

        for(CaseResult cr : failedTests) {
            failedTestsString = failedTestsString + "${cr.getFullDisplayName()}:\n${cr.getErrorDetails()}\n\n"
        }
        failedTestsString = failedTestsString + "```"
    }
    return failedTestsString
}
