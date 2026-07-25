import { useState } from "react";

export default function HOL03_scorecalculatorapp() {
  const [name, setName] = useState("");
  const [school, setSchool] = useState("");
  const [total, setTotal] = useState("");
  const [goal, setGoal] = useState("");
  const [average, setAverage] = useState(null);

  const calculateAverage = () => {
    if (goal > 0) {
      setAverage((Number(total) / Number(goal)).toFixed(2));
    } else {
      alert("Goal (subjects) must be greater than 0");
    }
  };

  return (
    <div>
      <h2>Score Calculator</h2>

      <div>
        <label>Name: </label>
        <input
          type="text"
          value={name}
          onChange={(e) => setName(e.target.value)}
        />
      </div>

      <div>
        <label>School: </label>
        <input
          type="text"
          value={school}
          onChange={(e) => setSchool(e.target.value)}
        />
      </div>

      <div>
        <label>Total Marks: </label>
        <input
          type="number"
          value={total}
          onChange={(e) => setTotal(e.target.value)}
        />
      </div>

      <div>
        <label>Number of Subjects: </label>
        <input
          type="number"
          value={goal}
          onChange={(e) => setGoal(e.target.value)}
        />
      </div>

      <br />

      <button onClick={calculateAverage}>Calculate</button>

      {average !== null && (
        <div style={{ marginTop: "20px" }}>
          <h3>Result</h3>
          <p>Name: {name}</p>
          <p>School: {school}</p>
          <p>Total: {total}</p>
          <p>Subjects: {goal}</p>
          <p>
            <strong>Average Score: {average}</strong>
          </p>
        </div>
      )}
    </div>
  );
}
