실행에 필요한 라이브러리는 다음과 같습니다.

dependencies {
    testCompile group: 'junit', name: 'junit', version: '4.12'

    // https://mvnrepository.com/artifact/net.htmlparser.jericho/jericho-html
    compile group: 'net.htmlparser.jericho', name: 'jericho-html', version: '3.4'

    // https://mvnrepository.com/artifact/org.jsoup/jsoup
    compile group: 'org.jsoup', name: 'jsoup', version: '1.10.1'

    // https://mvnrepository.com/artifact/mysql/mysql-connector-java
    compile group: 'mysql', name: 'mysql-connector-java', version: '5.1.6'
}