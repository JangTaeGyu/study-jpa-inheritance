# Spring Data JPA Study

> 어노테이션 @Inheritance, @DiscriminatorColumn, @DiscriminatorValue 활용하여 Entity 상속 관계 테스트를 합니다.

## 설명

Entity 상속을 실무에 적용을 할 수 있을지를 테스트 하기위해 카테고리 기능을 개발을 해보았습니다.

## 정책

- 카테고리는 3 Depth 까지만 등록이 가능 합니다.
- Entity 상태 변화를 전파하는 cascade 옵션을 사용 하지 않습니다.
- 조회한 카테고리에 부모 카테고리가 존재하면 getParentCategory() 로 조회가 가능 해야 합니다.
- 카테고리 삭제 할 때 자식 카테고리가 존재 하면 오류를 발생 합니다.

## Entity 목록

- 상속 카테고리(Category)
- 1 Depth 카테고리(MainCategory)
- 2 Depth 카테고리(SubCategory)
- 3 Depth 카테고리(SubSubCategory)

## 서비스 목록

- 카테고리 등록 서비스(CreateCategoryService)
- 카테고리 상세보기 서비스(CategoryQueryService)
- 카테고리 수정 서비스(UpdateCategoryService)
- 카테고리 삭  서비스(DeleteCategoryService)
