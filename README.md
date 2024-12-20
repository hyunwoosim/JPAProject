# 김영환 JPA의 프로젝트를 바탕으로 새로운 기능을 추가해서 만들 프로젝트이다.

## javax.validation

## BindingResult

## thymeleaf + spring

# 10.15

## 페이지 접근 오류

1. 회원가입 아이디,비밀번호,이메일,전화번호 추가중
   비밀번호 DTO를 만들어 비밀번호 인증과 암호화 하는 도중
   DB에 들어갈때 암호화 시키기위해 spring security 사용중 springConfig부분에 오류 발생
   수정하는중

# 10.16

## 페이지 접근 오류 해결

1. springConfig부분은 특정부분 인증 접근을 고치니 해결되었다

- 해결 전

```
.authorizeHttpRequests()
        .requestMatchers("/", "/home", "/register").permitAll()  // 특정 경로는 인증 없이 접근 가능
        .anyRequest().authenticated()  // 나머지 경로는 인증 필요
```

- requestMatchers("/", "/home", "/register") 이 부분이 특정 경로가 인증이 없이 접근가능하고 나머지 경로가 인증을 해야
- 접근할 수 있도록 설정하는 부분이었다.

- 해결 후

```
.authorizeHttpRequests()
        .anyRequest().permitAll()  // 특정 경로는 인증 없이 접근 가능
```

- anyRequest().permitAll()로 변경해주면 모든 페이지가 인증 없이 접근이 가능하게 바꿀 수 있다.

## 페이지 매핑 오류 403

- 회원가입을 할때 비밀번호와 비밀번호 확인이 일치 하지 않으면 @Valid 사용하여 검증을 하게 했는데
- 검증조차 되지 않고 /member/new페이지로 리다이렉트되기만 했다
- 이유는 바로 시큐리티 문제였다

- 해결 전

```
.authorizeHttpRequests()
.anyRequest().permitAll()  // 특정 경로는 인증 없이 접근 가능
```

- 해결 후

```
 .csrf().disable()
.authorizeHttpRequests()
```

- .csrf().disable() 이 부분을 추가 시켜줬어야 했다

### csrf란?

- CSRF(Cross-Site Request Forgery) 보호 기능을 비활성화하는 설정입니다. CSRF는 웹 애플리케이션에서 사용자가 의도하지 않은 요청을 서버에 보내는
  공격입니다. CSRF 공격을 방지하기 위해 Spring Security는 기본적으로 CSRF 보호를 활성화합니다.
- CSRF 토큰을 요구합니다. 사용자는 서버에서 제공하는 CSRF 토큰을 포함하여 요청을 보내야 하며, 서버는 이 토큰을 검증하여 요청의 유효성을 확인합니다.

## validation을 사용하여 hasErrors가 어떤에러인지 상황에 따라 문구 나가기

1. MemberController

```
//에러에게 맞게 문구 나가기
        if (result.hasErrors()) {

            Map<String, String> validatorResult = memberService.validateHandling(result);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
                
            }
```

2. MemberService
    - 실패한 필드들은 Map에 넣어 에러메세지를 보낸다
    -

```
// 회원 가입 시 유효성 검사
    public Map<String, String> validateHandling(BindingResult result) {
        Map<String, String> validateResult = new HashMap<>();

        for (FieldError error : result.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validateResult.put(validKeyName, error.getDefaultMessage());
        }
        return validateResult;
    }
```

3. PasswordDT0,MemberForm

```
============== PasswordDTO =============
 @NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min = 8, max = 20, message = "비밀번호는 최소 8자리에서 20자리 사이입니다.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,20}$",
        message = "최소 하나의 숫자,소문자,대문자,특수문자를 포함해야하며 공백이 없어야합니다.")
    private String password;

    @NotBlank(message = "비밀번호 확인은 필수입니다.")
    private String confirmPassword; // 비밀번호 일치 확인
    
    
 ============ MemberForm ============   
     @NotEmpty(message = "회원 이름은 필수입니다.")
    private String name;

    @Valid
    private PasswordDTO passwordDTO;

    @NotEmpty(message = "회원 이메일 필수입니다.")
    private String email;

    @NotEmpty(message = "회원 전화번호는 필수입니다.")
    private String phone;
```

4. hasErrors가 true인 경우
    - 이런식으로 메세지가 나온다

```
key = valid_passwordDTO.confirmPassword
validatorResult = 비밀번호 확인은 필수입니다.

key = valid_passwordDTO.password
validatorResult = 비밀번호는 필수입니다.
```

# 10.23

## 상품 등록 부분 변경

1. 책,앨범,영화 3가지의 상품을 등록할 수 있게 변경하였다.
2. ItemFactory를 사용하여 Category를 선택하면 선택한 카테리고에 맞게 상품을 등록할 수 있게 수정하였다.
    - createItemForm, ItemFactory, ItemController, ItemForm을 확인하면된다.

# 10.24

## 상품 전체 리스트 조회

1. dtype에 따라 카테고리를 조회할 수 있는 로직을 만들려고 하는중 dtype으로 구분하는 것 부터 시작했는데 dtype이 findAll()에서
   타임 리프에 적용이 안됨
2. Item 엔티티에 getDtype()로직을 추가하여 해결하였다.

- Item 엔티티

```
  // Dtype을 조회하는 로직
    public String getDtype() {
     return this.getClass().getSimpleName();
    }
```

- itemList.html

```
<td th:text="${item.dtype}"></td>
```

- item.dtype()를 넣으면 조회가 안되었다 이유는
  -item.dtype()로 접근할 수 없는 이유는 dtype이 메서드가 아니라 속성으로 처리되기 때문입니다. Thymeleaf에서 메서드를 호출할 때는 괄호를 사용하지 않아야
  하며, 속성에 접근할 때는 getter 메서드를 자동으로 호출하게 됩니다.

# 10.28

1. @RestController
    - @ResponseBody, @Controller과 합쳐진 에노테이션이다.

2. Member 엔티티가 직접 노출 되지 않게 수정중
    - MemberCreateDto를 사용하였다.
    - 비즈니스 로직을 추가하여 캡슐화하여 직접직인 필드 접근을 막았다.

# 10.30

1. memberUpdate 를 만드는중 updateItem을 참고 중이었는데 dto에서 setter로 엔티티에 접근중이었다. 근데 나는 엔티티에 setter를 사용하지
   않으려했기때문에
   Item 엔티티에 비즈니스 로직으로 changeName, changePrice등등을 추가하여 리펙토링을 하였다.
2. 그러던 중 아이템 리스트.html에도 문제가 발생했다. 상품이 없을때도 페이지가 출력되어야하는데 그것을 빼먹은 것이다. 바로 수정하여 완성

# 10.31

1. update에서 막혔는데 address의 기본 생성자가 producted이기 때문에 외부에서 초기화가 불가능 하였다.
2. 그래서 dto에 address를 만들어도 null값으로 넘어가는 오류가 발생하였다.
3. 그래서 createMemberDto와 updateMemberDto의 필드가 city,zipcode,street을 공통으로 사용해서 하나의 MemberDto로 합치고

```
public Address toAddress() {
        return new Address(city, street, zipcode);
    }

public void updateMember(Member member) {
        member.updateMember(this.name, this.email, this.phone, toAddress());
    }
```

4. city,zipcode,street을 address로 합치는 메서드와 updateMember를 하는 메서드를 만들어서 해결하였다.
5. MemberView는 address필드가 있는데 이유는 stream돌려서 정보를 가져오기때문이다.

# 11.14

## QueryDSL 사용

- gradle에서 사용중일때 taks에 queryDsl이 안뜸
- junit 4를 지우니 main에 뜨고있긴한데 더 정확하게 알아봐야함

## QueryDSL spring 3.x 대 Gradle 설정법

```
//Querydsl 추가
    implementation 'com.querydsl:querydsl-jpa:5.0.0:jakarta'
    annotationProcessor "com.querydsl:querydsl-apt:${dependencyManagement.importedProperties['querydsl.version']}:jakarta"
    annotationProcessor "jakarta.annotation:jakarta.annotation-api"
    annotationProcessor "jakarta.persistence:jakarta.persistence-api"
    
   def querydslSrcDir = 'src/main/generated'
   
   sourceSets {
    main {
        java {
            srcDirs += querydslSrcDir  // generated 디렉터리를 소스 루트에 추가
        }
    }
}
   
   clean {
       delete file(querydslSrcDir)
   } 
   tasks.withType(JavaCompile) {
    options.generatedSourceOutputDirectory = file(querydslSrcDir)
   }

```

- src/main/generated 디렉터리가 모듈 소스 인식되지 않을땐 sourceSets 을 추가시켜서 모듈 소스로 인식 시켜야한다.
- clean이 실행 될 때 src/main/generated 디렉터리를 삭제하는 역할을 한다.
- javaCompile 될 떄 Q파일을 src/main/generated 디렉터리에 생성해주는 역할을 한다.

## compileQueryDsl이 생성되지 않는 이유

- 기본 JavaCompile Task에 QueryDSL의 annotation processing 기능이 포함되어 있기 때문이다.
- 기본 컴파일 할때 기능이 같이 실행된다.