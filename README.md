<h1>🔐 JWT Authenticator</h1>

<p>A simple and clean <strong>Spring Boot Authentication and Authorization backend</strong> using <strong>JWT (JSON Web Token)</strong>.
You can connect any frontend (React, Angular, Vue, or even mobile apps) with this authenticator by hitting the exposed APIs.</p>

<hr>

<h2>📌 Features</h2>
<ul>
  <li>🔒 Spring Boot Security integration</li>
  <li>🔧 Custom Security Filters (no default filters)</li>
  <li>📄 JWT token generation and validation</li>
  <li>🗄️ SQL Database integration (MySQL by default — easily swappable)</li>
  <li>📑 Simple and clean API endpoints for registration and login</li>
  <li>🚀 Frontend-agnostic — use it with any frontend framework</li>
</ul>

<hr>

<h2>📊 Architecture Overview</h2>
<pre>
Frontend (React/Angular/Other)
         │
         ▼
POST /register  →  [Spring Boot Backend]  →  Save user (hashed password) in Database
POST /login     →  [Spring Boot Backend]  →  Verify credentials, issue JWT
Secured APIs    →  [Spring Boot Backend]  →  Validate JWT via custom filters
         │
         ▼
       Database (MySQL)
</pre>

<hr>

<h2>📂 Project Structure</h2>
<pre>
src/main/java/com/example/jwtauthenticator/
│
├── controller/          # Exposes APIs like /register, /login
├── model/               # User entity class
├── repository/          # JPA repository interfaces
├── security/
│   ├── JWTUtil.java     # Token generation & validation
│   ├── SecurityConfig.java  # Spring Security configuration
│   └── JwtAuthFilter.java   # Custom JWT authentication filter
├── service/             # Business logic (user management, password hashing)
└── JwtAuthenticatorApplication.java  # Main class
</pre>

<hr>

<h2>🚀 How to Use This JWT Authenticator</h2>
<ol>
  <li><strong>Clone the repository</strong>
    <pre>git clone https://github.com/your-username/your-repo-name.git
cd your-repo-name</pre>
  </li>

  <li><strong>Configure the Database</strong>
    <ul>
      <li>Create a MySQL database (or any other supported SQL DB)</li>
      <li>Import the provided SQL script (if available) or let JPA create the tables for you.</li>
    </ul>
  </li>

  <li><strong>Update <code>application.properties</code></strong>
    <pre>
spring.datasource.url=jdbc:mysql://localhost:3306/your_db_name
spring.datasource.username=your_username
spring.datasource.password=your_password
    </pre>
  </li>

  <li><strong>Run the Application</strong>
    <pre>./mvnw spring-boot:run</pre>
  </li>
</ol>

<hr>

<h2>🛠️ Exposed API Endpoints</h2>
<table border="1" cellspacing="0" cellpadding="5">
  <thead>
    <tr>
      <th>Method</th>
      <th>Endpoint</th>
      <th>Description</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>POST</td>
      <td>/register</td>
      <td>Register a new user</td>
    </tr>
    <tr>
      <td>POST</td>
      <td>/login</td>
      <td>Login user, returns JWT token</td>
    </tr>
    <tr>
      <td>GET/POST/PUT/DELETE</td>
      <td>/secured-endpoint</td>
      <td>Accessible only with a valid JWT</td>
    </tr>
  </tbody>
</table>

<p><strong>JWT should be sent in the <code>Authorization</code> header like:</strong></p>
<pre>Authorization: Bearer &lt;token&gt;</pre>

<hr>

<h2>🔒 Security Implementation</h2>
<ul>
  <li>Passwords are hashed using <strong>BCrypt</strong></li>
  <li>JWT generated with expiry and signature</li>
  <li>Custom authentication filter intercepts requests and validates tokens</li>
  <li>Only <code>/register</code> and <code>/login</code> are publicly accessible</li>
</ul>

<hr>

<h2>📑 How JWT Works Here</h2>
<ol>
  <li>User registers → hashed password saved to DB</li>
  <li>User logs in → JWT issued on successful credential match</li>
  <li>For secured APIs → client sends JWT in <code>Authorization</code> header</li>
  <li>Backend filters intercept requests, extract and validate JWT</li>
  <li>If valid → allow access, else respond with 401 Unauthorized</li>
</ol>

<hr>

<h2>🌐 Connect Any Frontend</h2>
<p>You can build your own frontend using <strong>React, Angular, Vue, Flutter</strong> or even <strong>Postman</strong> and hit these endpoints directly.</p>

<p><strong>Example usage in React:</strong></p>
<pre>
axios.post('http://localhost:8080/login', { email, password })
  .then(response =&gt; localStorage.setItem('token', response.data.token));
</pre>

<hr>




<h2>🙌 Contributions</h2>
<p>Open to pull requests and collaborations!</p>
