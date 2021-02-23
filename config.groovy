environments {
    local {
        infrastructure {
            spring {
                datasource {
                    changelog = 'infrastructure/src/main/resources/db/changelog_local.xml'
                    url = 'jdbc:mariadb://localhost:13306/karibou_local?allowPublicKeyRetrieval=true&useSSL=false'
                    username = 'karibou_user'
                    password = 'devsecret'
                }
            }
        }
    }

    test {
        infrastructure {
            spring {
                datasource {
                    changelog = 'src/main/resources/db/changelog_master.xml'
                    url = 'jdbc:mariadb://localhost:13307/karibou_test?allowPublicKeyRetrieval=true&useSSL=false'
                    username = 'karibou_user'
                    password = 'testsecret'
                }
            }
        }
    }
}