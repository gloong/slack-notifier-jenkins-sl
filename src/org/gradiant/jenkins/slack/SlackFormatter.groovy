package org.gradiant.jenkins.slack


String format(String title = '', String message = '', String testSummary = '') {
  def helper = new JenkinsHelper()

  def project = helper.getProjectName()
  def branch = helper.getBranchName()
  def buildNumber = helper.getBuildNumber()
  def url = helper.getAbsoluteUrl()

  def result = "*Application:*\n${project}\n*Branch:*\n${branch} \n*Build No:*\n#${buildNumber} \n*Commit Info:*\n${title.trim()} (<${url}|Open>)"
  if(message) result = result + "\n*Reason:*\n ${message.trim()}"
  if(testSummary) result = result + "\n${testSummary}"

  return result
}
