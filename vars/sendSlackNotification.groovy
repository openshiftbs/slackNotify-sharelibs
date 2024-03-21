def call(String buildStatus = 'STARTED') {
    // Mapping build status to color and color code
    def colorMap = [
        'STARTED': ['YELLOW', '#FFFF00'],
        'SUCCESS': ['GREEN', '#00FF00'],
        'FAILURE': ['RED', '#FF0000']
    ]

    // Default to SUCCESS if buildStatus is null
    buildStatus = buildStatus ?: 'SUCCESS'
  
    // Extract color name and code based on build status
    def [colorName, colorCode] = colorMap.get(buildStatus.toUpperCase(), ['RED', '#FF0000'])
  
    // Construct subject and summary strings
    def subject = "${buildStatus}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'"
    def summary = "${subject} (${env.BUILD_URL})"
  
    // Calling the slackSend function to send notifications
    slackSend(color: colorCode, message: summary)
}
