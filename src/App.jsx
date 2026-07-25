import { useState } from "react";

import HOL01 from "./handson/HOL01_myfirstreact";
import HOL02 from "./handson/HOL02_StudentApp";
import HOL03 from "./handson/HOL03_scorecalculatorapp";
import HOL04 from "./handson/HOL04_blogapp";
import HOL05 from "./handson/HOL05_styling";
import HOL09 from "./handson/HOL09_cricketapp";
import HOL10 from "./handson/HOL10_officespacerentalapp";
import HOL11 from "./handson/HOL11_eventexamplesapp";
import HOL12 from "./handson/HOL12_ticketbookingapp";
import HOL13 from "./handson/HOL13_bloggerapp";

const HANDSON = {
  "HOL 1 • My First React": <HOL01 />,
  "HOL 2 • Student App": <HOL02 />,
  "HOL 3 • Score Calculator": <HOL03 />,
  "HOL 4 • Blog App": <HOL04 />,
  "HOL 5 • Styling": <HOL05 />,
  "HOL 9 • Cricket App": <HOL09 />,
  "HOL 10 • Office Space Rental": <HOL10 />,
  "HOL 11 • Event Examples": <HOL11 />,
  "HOL 12 • Ticket Booking": <HOL12 />,
  "HOL 13 • Blogger App": <HOL13 />,
};

export default function App() {
  const labels = Object.keys(HANDSON);
  const [active, setActive] = useState(labels[0]);

  return (
    <div className="app">
      <header className="hero">
        <h1>⚛ ReactJS Mandatory Hands-on Dashboard</h1>

        <p className="sub">
          Week 5 ReactJS Hands-on Exercises (HOL 1–5 & HOL 9–13)
        </p>

        <div className="stats">
          <div className="stat-card">
            <h3>10</h3>
            <p>Exercises</p>
          </div>

          <div className="stat-card">
            <h3>React</h3>
            <p>Components</p>
          </div>

          <div className="stat-card">
            <h3>Hooks</h3>
            <p>Lifecycle</p>
          </div>
        </div>
      </header>

      <nav className="tabs">
        {labels.map((label) => (
          <button
            key={label}
            className={active === label ? "tab active" : "tab"}
            onClick={() => setActive(label)}
          >
            {label}
          </button>
        ))}
      </nav>

      <section className="demo">
        <h2>{active}</h2>

        <div className="content">
          {HANDSON[active]}
        </div>
      </section>

      <footer className="footer">
        <p>
          Developed using <strong>React + Vite</strong>
        </p>

        <p>
          Cognizant ReactJS Mandatory Hands-on Dashboard
        </p>
      </footer>
    </div>
  );
}