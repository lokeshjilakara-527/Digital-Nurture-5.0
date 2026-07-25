import { useState } from "react";
import styles from "./HOL05_styling.module.css";

export default function HOL05_styling() {
  const [cohorts, setCohorts] = useState([
    { id: 1, name: "React Cohort - Jan", tech: "React", status: "ongoing" },
    { id: 2, name: "Java Cohort - Nov", tech: "Java", status: "completed" },
  ]);

  const [name, setName] = useState("");
  const [tech, setTech] = useState("");
  const [status, setStatus] = useState("ongoing");

  const addCohort = () => {
    if (!name || !tech) {
      alert("Please enter all fields");
      return;
    }

    const newCohort = {
      id: cohorts.length + 1,
      name,
      tech,
      status,
    };

    setCohorts([...cohorts, newCohort]);

    setName("");
    setTech("");
    setStatus("ongoing");
  };

  return (
    <div>
      <h2>Cohort Dashboard</h2>

      <input
        type="text"
        placeholder="Cohort Name"
        value={name}
        onChange={(e) => setName(e.target.value)}
      />

      <br /><br />

      <input
        type="text"
        placeholder="Technology"
        value={tech}
        onChange={(e) => setTech(e.target.value)}
      />

      <br /><br />

      <select
        value={status}
        onChange={(e) => setStatus(e.target.value)}
      >
        <option value="ongoing">Ongoing</option>
        <option value="completed">Completed</option>
      </select>

      <br /><br />

      <button onClick={addCohort}>Add Cohort</button>

      <hr />

      {cohorts.map((c) => {
        const statusStyle = {
          color: c.status === "ongoing" ? "green" : "gray",
          fontWeight: "bold",
        };

        return (
          <div key={c.id} className={styles.card}>
            <p className={styles.title}>{c.name}</p>
            <p>Technology: {c.tech}</p>
            <p style={statusStyle}>Status: {c.status}</p>
          </div>
        );
      })}
    </div>
  );
}