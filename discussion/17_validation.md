- https://www.baeldung.com/spring-boot-bean-validation
- https://www.baeldung.com/spring-mvc-custom-validator

The Jakarta Bean Validation API (formerly known as the Java Bean Validation API or JSR 380) provides a number
of built-in constraint annotations that can be used to validate fields in Java classes. Here are the commonly used 
built-in validation annotations:

### Common Validation Annotations

1. **@NotNull**: Ensures that the annotated field is not `null`.
   ```java
   @NotNull(message = "Field must not be null")
   private String field;
   ```

2. **@NotEmpty**: Ensures that the annotated string, collection, map, or array is not `null` and not empty.
   ```java
   @NotEmpty(message = "Field must not be empty")
   private String field;
   ```

3. **@NotBlank**: Ensures that the annotated string is not `null` and the trimmed length is greater than 0.
   ```java
   @NotBlank(message = "Field must not be blank")
   private String field;
   ```

4. **@Size**: Validates that the annotated string, collection, map, or array size is within the specified bounds.
   ```java
   @Size(min = 2, max = 14, message = "Size must be between 2 and 14")
   private String field;
   ```

5. **@Min**: Validates that the annotated value is greater than or equal to the specified minimum.
   ```java
   @Min(value = 10, message = "Value must be at least 10")
   private int field;
   ```

6. **@Max**: Validates that the annotated value is less than or equal to the specified maximum.
   ```java
   @Max(value = 100, message = "Value must be at most 100")
   private int field;
   ```

7. **@Email**: Validates that the annotated string is a well-formed email address.
   ```java
   @Email(message = "Email should be valid")
   private String email;
   ```

8. **@Pattern**: Validates that the annotated string matches the specified regular expression.
   ```java
   @Pattern(regexp = "^[A-Za-z]+$", message = "Must contain only letters")
   private String field;
   ```

9. **@Past**: Validates that the annotated date is in the past.
   ```java
   @Past(message = "Date must be in the past")
   private LocalDate date;
   ```

10. **@PastOrPresent**: Validates that the annotated date is in the past or present.
    ```java
    @PastOrPresent(message = "Date must be in the past or present")
    private LocalDate date;
    ```

11. **@Future**: Validates that the annotated date is in the future.
    ```java
    @Future(message = "Date must be in the future")
    private LocalDate date;
    ```

12. **@FutureOrPresent**: Validates that the annotated date is in the future or present.
    ```java
    @FutureOrPresent(message = "Date must be in the future or present")
    private LocalDate date;
    ```

13. **@Positive**: Validates that the annotated value is greater than 0.
    ```java
    @Positive(message = "Value must be positive")
    private int field;
    ```

14. **@PositiveOrZero**: Validates that the annotated value is greater than or equal to 0.
    ```java
    @PositiveOrZero(message = "Value must be positive or zero")
    private int field;
    ```

15. **@Negative**: Validates that the annotated value is less than 0.
    ```java
    @Negative(message = "Value must be negative")
    private int field;
    ```

16. **@NegativeOrZero**: Validates that the annotated value is less than or equal to 0.
    ```java
    @NegativeOrZero(message = "Value must be negative or zero")
    private int field;
    ```

17. **@Digits**: Validates that the annotated value is a number and has up to the specified number of integer and 
fraction digits.
    ```java
    @Digits(integer = 5, fraction = 2, message = "Number must have up to 5 integer digits and 2 fraction digits")
    private BigDecimal field;
    ```

18. **@DecimalMin**: Validates that the annotated value is a number greater than or equal to the specified minimum.
    ```java
    @DecimalMin(value = "10.5", message = "Value must be at least 10.5")
    private BigDecimal field;
    ```

19. **@DecimalMax**: Validates that the annotated value is a number less than or equal to the specified maximum.
    ```java
    @DecimalMax(value = "100.5", message = "Value must be at most 100.5")
    private BigDecimal field;
    ```

20. **@AssertTrue**: Validates that the annotated value is `true`.
    ```java
    @AssertTrue(message = "Must be true")
    private boolean field;
    ```

21. **@AssertFalse**: Validates that the annotated value is `false`.
    ```java
    @AssertFalse(message = "Must be false")
    private boolean field;
    ```

22. **@Null**: Validates that the annotated field is `null`.
    ```java
    @Null(message = "Must be null")
    private String field;
    ```

23. **@CreditCardNumber**: Validates that the annotated string is a valid credit card number (note: this is specific 
to Hibernate Validator).
    ```java
    @CreditCardNumber(message = "Invalid credit card number")
    private String creditCardNumber;
    ```

24. **@URL**: Validates that the annotated string is a valid URL (note: this is specific to Hibernate Validator).
    ```java
    @URL(message = "Invalid URL")
    private String url;
    ```

