# Spring Data JPA Study

> 해당 프로젝트는 서비스만 개발하여 별도 API 호출은 불가능 합니다.

### 학습목표

- Entity 상속을 통하여 비지니스 로직에 카테고리를 구현이 가능한지 테스트 하는 목적이 있습니다.

### 개발환경

- Java 17
- Spring Boot 2.7.*
- Spring Data JPA
- PostgreSQL
- Maven

### 카테고리 CRUD 기능 정의

- 카테고리 등록은 3 Depth 까지만 등록이 가능 합니다.
- 카테고리 상세보기 할 때 부모 카테고리가 있으면 같이 조회를 합니다.
- 카테고리 수정 할 때 변경된 값만 업데이트를 진행 합니다.
- 카테고리 삭제 할 때 자식 카테고리 존재 하면 삭제 진행을 하지 않습니다.

### 실행하기

```shell
# 프로젝트 다운로드
git clone https://github.com/JangTaeGyu/study-jpa-inheritance.git

cd ./study-jpa-inheritance

# 테스트 실행
mvn test
```

### 카테고리 서비스 실행

#### 카테고리 등록

```sql
-- 메인 카테고리 생성
insert into categories (created_at, description, name, updated_at, type) values (?, ?, ?, ?, 'MAIN');

-- 2 Depth 카테고리 생성
select category0_.id as id2_0_0_, category0_.created_at as created_3_0_0_, category0_.description as descript4_0_0_, category0_.name as name5_0_0_, category0_.type as type1_0_0_, category0_.updated_at as updated_6_0_0_, category0_.parent_main_id as parent_m7_0_0_, category0_.parent_sub_id as parent_s8_0_0_, maincatego1_.id as id2_0_1_, maincatego1_.created_at as created_3_0_1_, maincatego1_.description as descript4_0_1_, maincatego1_.name as name5_0_1_, maincatego1_.type as type1_0_1_, maincatego1_.updated_at as updated_6_0_1_ from categories category0_ left outer join categories maincatego1_ on category0_.parent_main_id=maincatego1_.id where category0_.id=?;
insert into categories (created_at, description, name, updated_at, parent_main_id, type) values (?, ?, ?, ?, ?, 'SUB');

-- 3 Depth 카테고리 생성
select category0_.id as id2_0_0_, category0_.created_at as created_3_0_0_, category0_.description as descript4_0_0_, category0_.name as name5_0_0_, category0_.type as type1_0_0_, category0_.updated_at as updated_6_0_0_, category0_.parent_main_id as parent_m7_0_0_, category0_.parent_sub_id as parent_s8_0_0_, maincatego1_.id as id2_0_1_, maincatego1_.created_at as created_3_0_1_, maincatego1_.description as descript4_0_1_, maincatego1_.name as name5_0_1_, maincatego1_.type as type1_0_1_, maincatego1_.updated_at as updated_6_0_1_ from categories category0_ left outer join categories maincatego1_ on category0_.parent_main_id=maincatego1_.id where category0_.id=?;
insert into categories (created_at, description, name, updated_at, parent_sub_id, type) values (?, ?, ?, ?, ?, 'SUBSUB');
```

#### 카테고리 상세보기

```sql
-- 메인 카테고리 조회
-- Query 에서는 메인 카테고리와 부모 카테고리를 조회를 한다. 
-- 예상했던 Query 는 select category0_.id as id2_0_0_, category0_.created_at as created_3_0_0_, category0_.description as descript4_0_0_, category0_.name as name5_0_0_, category0_.type as type1_0_0_, category0_.updated_at as updated_6_0_0_, category0_.parent_main_id as parent_m7_0_0_, category0_.parent_sub_id as parent_s8_0_0_ from categories category0_ where category0_.id=? 인데 ... 
select category0_.id as id2_0_0_, category0_.created_at as created_3_0_0_, category0_.description as descript4_0_0_, category0_.name as name5_0_0_, category0_.type as type1_0_0_, category0_.updated_at as updated_6_0_0_, category0_.parent_main_id as parent_m7_0_0_, category0_.parent_sub_id as parent_s8_0_0_, maincatego1_.id as id2_0_1_, maincatego1_.created_at as created_3_0_1_, maincatego1_.description as descript4_0_1_, maincatego1_.name as name5_0_1_, maincatego1_.type as type1_0_1_, maincatego1_.updated_at as updated_6_0_1_ from categories category0_ left outer join categories maincatego1_ on category0_.parent_main_id=maincatego1_.id where category0_.id=?

-- 2 Depth 카테고리 조회
-- 2 Depth 카테고리와 부모 카테고리를 조회 한다.
select category0_.id as id2_0_0_, category0_.created_at as created_3_0_0_, category0_.description as descript4_0_0_, category0_.name as name5_0_0_, category0_.type as type1_0_0_, category0_.updated_at as updated_6_0_0_, category0_.parent_main_id as parent_m7_0_0_, category0_.parent_sub_id as parent_s8_0_0_, maincatego1_.id as id2_0_1_, maincatego1_.created_at as created_3_0_1_, maincatego1_.description as descript4_0_1_, maincatego1_.name as name5_0_1_, maincatego1_.type as type1_0_1_, maincatego1_.updated_at as updated_6_0_1_ from categories category0_ left outer join categories maincatego1_ on category0_.parent_main_id=maincatego1_.id where category0_.id=?

-- 3 Depth 카테고리 조회
-- 3 Depth 카테고리와 2 Depth 카테고리를 조회하고 이후 2 Depth 카테고리와 부모 카테고리를 조회 한다.
select category0_.id as id2_0_0_, category0_.created_at as created_3_0_0_, category0_.description as descript4_0_0_, category0_.name as name5_0_0_, category0_.type as type1_0_0_, category0_.updated_at as updated_6_0_0_, category0_.parent_main_id as parent_m7_0_0_, category0_.parent_sub_id as parent_s8_0_0_, maincatego1_.id as id2_0_1_, maincatego1_.created_at as created_3_0_1_, maincatego1_.description as descript4_0_1_, maincatego1_.name as name5_0_1_, maincatego1_.type as type1_0_1_, maincatego1_.updated_at as updated_6_0_1_ from categories category0_ left outer join categories maincatego1_ on category0_.parent_main_id=maincatego1_.id where category0_.id=?;
select subcategor0_.id as id2_0_0_, subcategor0_.created_at as created_3_0_0_, subcategor0_.description as descript4_0_0_, subcategor0_.name as name5_0_0_, subcategor0_.type as type1_0_0_, subcategor0_.updated_at as updated_6_0_0_, subcategor0_.parent_main_id as parent_m7_0_0_, maincatego1_.id as id2_0_1_, maincatego1_.created_at as created_3_0_1_, maincatego1_.description as descript4_0_1_, maincatego1_.name as name5_0_1_, maincatego1_.type as type1_0_1_, maincatego1_.updated_at as updated_6_0_1_ from categories subcategor0_ left outer join categories maincatego1_ on subcategor0_.parent_main_id=maincatego1_.id where subcategor0_.id=? and subcategor0_.type='SUB';
```

#### 카테고리 수정

```sql
-- 카테고리 상세보기에서 발생한 Query 가 동일 하게 발생 후 아래 수정 을 진행 합니다.
-- 3 Depth 카테고리 수정 했을 때에는 부모 카테고리에 대한 연관 관계가 @ManyToOne 이고 fetch default EAGER 이기 때문에 Query 가 2번 발생 한다.
update categories set description=?, name=?, updated_at=? where id=?
```

#### 카테고리 삭제

```sql
-- @ManyToOne 에 대한 cascade 설정을 하지 않아서 카테고리를 삭제를 해도 자식 카테고리 삭제가 안되고 오류가 발생한다.
-- 이 문제를 해결 하기 위해 서비스에 자식 카테고리 존재 유무를 체크하는 코드가 추가 되었습니다.
delete from categories where id=?
```
