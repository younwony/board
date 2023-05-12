# Infra 구성 시작해보기.

- AWS 프리티어 계정 생성 (무료) - [링크](https://aws.amazon.com/ko/free/?all-free-tier.sort-by=item.additionalFields.SortRank&all-free-tier.sort-order=asc&awsf.Free%20Tier%20Types=*all&awsf.Free%20Tier%20Categories=*all) 
  - 프리티어 정리 [참고](https://inpa.tistory.com/entry/AWS-%F0%9F%92%B0-%ED%94%84%EB%A6%AC%ED%8B%B0%EC%96%B4-%EC%9A%94%EA%B8%88-%ED%8F%AD%ED%83%84-%EB%B0%A9%EC%A7%80-%F0%9F%92%B8-%EB%AC%B4%EB%A3%8C-%EC%82%AC%EC%9A%A9%EB%9F%89-%EC%A0%95%EB%A6%AC)
  - 계정 보안 설정 (해킹 방지) [참고](https://inpa.tistory.com/entry/AWS-%F0%9F%93%9A-%EC%95%84%EB%A7%88%EC%A1%B4-%EA%B0%80%EC%9E%85-%EC%A0%88%EC%B0%A8-%C2%A7-%EB%B3%B4%EC%95%88-%EC%84%A4%EC%A0%95-MFA-IAM-%EC%A0%95%EB%A6%AC)

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