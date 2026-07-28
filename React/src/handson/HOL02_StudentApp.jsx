import { useState } from "react";

function Home() {
  return <p>Welcome to the Home page of Student Management Portal</p>;
}

function About() {
  return <p>Welcome to the About page of the Student Management Portal</p>;
}

function Contact() {
  return <p>Welcome to the Contact page of the Student Management Portal</p>;
}

export default function HOL02_StudentApp() {
  const [page, setPage] = useState("");

  return (
    <div>
      <h2>Student Management Portal</h2>

      <select value={page} onChange={(e) => setPage(e.target.value)}>
        <option value="">Select a Page</option>
        <option value="home">Home</option>
        <option value="about">About</option>
        <option value="contact">Contact</option>
      </select>

      <hr />

      {page === "home" && <Home />}
      {page === "about" && <About />}
      {page === "contact" && <Contact />}
    </div>
  );
}