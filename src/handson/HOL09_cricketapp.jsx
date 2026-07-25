import { useState } from "react";

function ListofPlayers({ players }) {
  const below70 = players.filter((p) => p.score < 70);

  return (
    <div>
      <h3>All Players (map)</h3>
      <ul>
        {players.map((p, i) => (
          <li key={i}>
            {p.name} - {p.score}
          </li>
        ))}
      </ul>

      <h3>Players with score below 70 (filter)</h3>
      <ul>
        {below70.map((p, i) => (
          <li key={i}>
            {p.name} - {p.score}
          </li>
        ))}
      </ul>
    </div>
  );
}

function IndianPlayers() {
  const T20players = ["Rohit", "Kohli", "Suryakumar"];
  const RanjiTrophy = ["Sarfaraz", "Easwaran"];

  const allPlayers = [...T20players, ...RanjiTrophy];

  const [odd1, , odd3] = allPlayers;
  const [, even2, , even4] = allPlayers;

  return (
    <div>
      <h3>Merged Players (Spread Operator)</h3>
      <p>{allPlayers.join(", ")}</p>

      <p>
        <b>Odd Team Players:</b> {odd1}, {odd3}
      </p>

      <p>
        <b>Even Team Players:</b> {even2}, {even4}
      </p>
    </div>
  );
}

export default function HOL09_cricketapp() {
  const [players, setPlayers] = useState([
    { name: "Rohit", score: 85 },
    { name: "Kohli", score: 92 },
    { name: "Gill", score: 65 },
    { name: "Iyer", score: 78 },
    { name: "Rahul", score: 55 },
  ]);

  const [name, setName] = useState("");
  const [score, setScore] = useState("");
  const [flag, setFlag] = useState(true);

  const addPlayer = () => {
    if (!name || score === "") {
      alert("Enter player name and score");
      return;
    }

    setPlayers([
      ...players,
      {
        name,
        score: Number(score),
      },
    ]);

    setName("");
    setScore("");
  };

  return (
    <div>
      <h2>Cricket App</h2>

      <input
        type="text"
        placeholder="Player Name"
        value={name}
        onChange={(e) => setName(e.target.value)}
      />

      <br />
      <br />

      <input
        type="number"
        placeholder="Score"
        value={score}
        onChange={(e) => setScore(e.target.value)}
      />

      <br />
      <br />

      <button onClick={addPlayer}>Add Player</button>

      <br />
      <br />

      <button onClick={() => setFlag(!flag)}>
        {flag ? "Show Indian Players" : "Show Player List"}
      </button>

      <hr />

      {flag ? (
        <ListofPlayers players={players} />
      ) : (
        <IndianPlayers />
      )}
    </div>
  );
}