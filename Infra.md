# Infra 구성 시작해보기.

- AWS 프리티어 계정 생성 (무료) - [링크](https://aws.amazon.com/ko/free/?all-free-tier.sort-by=item.additionalFields.SortRank&all-free-tier.sort-order=asc&awsf.Free%20Tier%20Types=*all&awsf.Free%20Tier%20Categories=*all) 

 - EC2 생성 - [참고](https://velog.io/@jonghyun3668/SpringBoot-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-EC2-%EB%B0%B0%ED%8F%AC%ED%95%98%EA%B8%B0)
   - Amazon Linux 2 AMI 
   - 보안 설정(inbound (Port 설정))
   - git 설치
   - git ssh 설정
   - java 설치 -(sudo yum install -y java-17-amazon-corretto-devel.x86_64)
   - gradlew build 실행 (./gradlew build) 
     - generated-snippets 폴더 생성 확인 (asciidoc 구성 관련 폴더)
   - java -jar 실행 (java -jar build/libs/*.jar)
   - 브라우저에서 접속 확인
   - 배포 스크립트 작성 (deploy.sh) [참고](https://yeonyeon.tistory.com/52)
 - CI/CD
 - Jenkins 파이프 라인 구축
 - Docker
 - Kubernetes