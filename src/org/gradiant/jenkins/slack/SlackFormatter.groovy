package org.gradiant.jenkins.slack


String format(String title = '', String message = '', String testSummary = '') {
  def helper = new JenkinsHelper()

  def project = helper.getProjectName()
  def branch = helper.getBranchName()
  def buildNumber = helper.getBuildNumber()
  def url = helper.getAbsoluteUrl()

  def result = "Application:\n<b>${project}</b>\n<b>Branch:</b>\n${branch} - #${buildNumber} ${title.trim()} (<${url}|Open>)"
  if(message) result = result + "\n<b>Commit:</b>\n\t ${message.trim()}"
  if(testSummary) result = result + "\n<b>${testSummary}</b>"

  return result
}
