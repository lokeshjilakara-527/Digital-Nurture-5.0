import { useState } from "react";

function BookDetails({ title, author }) {
  return (
    <div>
      <h3>📘 Book Details</h3>
      <p>Title: {title}</p>
      <p>Author: {author}</p>
    </div>
  );
}

function BlogDetails({ title }) {
  return (
    <div>
      <h3>📝 Blog Details</h3>
      <p>Blog: {title}</p>
    </div>
  );
}

function CourseDetails({ title }) {
  return (
    <div>
      <h3>🎓 Course Details</h3>
      <p>Course: {title}</p>
    </div>
  );
}

export default function HOL13_bloggerapp() {
  const [view, setView] = useState("book");

  const [bookTitle, setBookTitle] = useState("Clean Code");
  const [author, setAuthor] = useState("Robert C. Martin");
  const [blogTitle, setBlogTitle] = useState("Understanding React Hooks");
  const [courseTitle, setCourseTitle] = useState("Java Full Stack Engineer");

  // Technique 1: if-else with element variable
  let content;
  if (view === "book") {
    content = <BookDetails title={bookTitle} author={author} />;
  } else if (view === "blog") {
    content = <BlogDetails title={blogTitle} />;
  } else {
    content = <CourseDetails title={courseTitle} />;
  }

  return (
    <div>
      <h2>Blogger App</h2>

      <h3>Update Details</h3>

      <input
        type="text"
        placeholder="Book Title"
        value={bookTitle}
        onChange={(e) => setBookTitle(e.target.value)}
      />
      <br /><br />

      <input
        type="text"
        placeholder="Author"
        value={author}
        onChange={(e) => setAuthor(e.target.value)}
      />
      <br /><br />

      <input
        type="text"
        placeholder="Blog Title"
        value={blogTitle}
        onChange={(e) => setBlogTitle(e.target.value)}
      />
      <br /><br />

      <input
        type="text"
        placeholder="Course Title"
        value={courseTitle}
        onChange={(e) => setCourseTitle(e.target.value)}
      />

      <hr />

      <button onClick={() => setView("book")}>Book</button>{" "}
      <button onClick={() => setView("blog")}>Blog</button>{" "}
      <button onClick={() => setView("course")}>Course</button>

      <hr />

      <h4>1. If / Else (Element Variable)</h4>
      {content}

      <hr />

      <h4>2. Ternary Operator</h4>
      {view === "book" ? (
        <BookDetails title={bookTitle} author={author} />
      ) : (
        <p>Not the Book View</p>
      )}

      <hr />

      <h4>3. Logical &&</h4>
      {view === "course" && (
        <CourseDetails title={courseTitle} />
      )}

      <hr />

      <h4>4. Switch Statement</h4>
      {(() => {
        switch (view) {
          case "book":
            return (
              <BookDetails title={bookTitle} author={author} />
            );
          case "blog":
            return <BlogDetails title={blogTitle} />;
          case "course":
            return <CourseDetails title={courseTitle} />;
          default:
            return <p>No Data</p>;
        }
      })()}
    </div>
  );
}