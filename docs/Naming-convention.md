## Kotlin Android Project Naming Convention

### 1. Class and File Names
- Class names should use UpperCamelCase.
- File names should match the class name, starting with lowercase, and using the `.kt` extension.

### 2. Package Names
- Package names should all be lowercase.
- They may include a domain name reversed. (e.g., `com.example.myapp`)

### 3. Variable and Function Names
- Variable and function names should use lowerCamelCase.
- Use meaningful names to enhance code readability.
- Use nouns or verbs to clearly indicate the purpose of variables and functions.

### 4. Constants
- Constants should be all uppercase, with words separated by underscores.
- Declare constants at the top of the file or class, minimizing their scope where possible.

### 5. Resource Files
- Resource files should be lowercase, with underscores if necessary between words. (e.g., `activity_main.xml`)
- Resource file names should clearly indicate the role of the resource.

### 6. Layout Files
- Layout file names should clearly indicate the role of the layout component where they are used. (e.g., `fragment_home.xml`, `activity_main.xml`)

### 7. ID Names
- IDs used in layout XML files should use lowerCamelCase.
- Use clear names that indicate the role and type of the view. (e.g., `textViewUsername`, `buttonSubmit`)

### 8. Resource Directories
- Resource directories should be in plural form. (e.g., `drawable`, `values`)
- Group related resources into directories for readability. (e.g., `drawable`, `drawable-hdpi`, `drawable-mdpi`)
