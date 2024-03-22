def call(String buildStatus = 'STARTED') {
    // Checking if buildStatus is null or empty, defaulting to 'SUCCESS' if so
    buildStatus = buildStatus ?: 'SUCCESS'

    // Default values
    def colorName = 'RED'
    def colorCode = '#FF0000'
    def subject = "${buildStatus}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'"
    def summary = "${subject} (${env.BUILD_URL})"

    // Override default values based on build status
    switch (buildStatus) {
        case 'STARTED':
            colorName = 'YELLOW'
            colorCode = '#FFFF00'
            break
        case 'SUCCESS':
            colorName = 'GREEN'
            colorCode = '#00FF00'
            break
        default:
            colorName = 'RED'
            colorCode = '#FF0000'
    }

    // Calling the slackSend function to Send notifications.
    slackSend(color: colorCode, message: summary)
}
