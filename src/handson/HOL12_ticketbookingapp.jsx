import { useState } from "react";

function GuestPage({ flights }) {
  return (
    <div>
      <h3>Flight Details (Guest)</h3>

      <ul>
        {flights.map((flight, index) => (
          <li key={index}>
            {flight.number} · {flight.route}
          </li>
        ))}
      </ul>

      <p>Please login to book tickets.</p>
    </div>
  );
}

function UserPage({ flights }) {
  const bookTicket = (flight) => {
    alert(`Ticket booked successfully for ${flight.number}`);
  };

  return (
    <div>
      <h3>Welcome, User</h3>

      <ul>
        {flights.map((flight, index) => (
          <li key={index}>
            {flight.number} · {flight.route}{" "}
            <button onClick={() => bookTicket(flight)}>
              Book
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default function HOL12_ticketbookingapp() {
  const [loggedIn, setLoggedIn] = useState(false);

  const [flights, setFlights] = useState([
    {
      number: "AI-202",
      route: "DEL → BLR",
    },
    {
      number: "6E-455",
      route: "BLR → HYD",
    },
  ]);

  const [flightNo, setFlightNo] = useState("");
  const [route, setRoute] = useState("");

  const addFlight = () => {
    if (!flightNo || !route) {
      alert("Enter flight details");
      return;
    }

    setFlights([
      ...flights,
      {
        number: flightNo,
        route: route,
      },
    ]);

    setFlightNo("");
    setRoute("");
  };

  return (
    <div>
      <h2>Ticket Booking App</h2>

      <input
        type="text"
        placeholder="Flight Number"
        value={flightNo}
        onChange={(e) => setFlightNo(e.target.value)}
      />

      <br /><br />

      <input
        type="text"
        placeholder="Route (e.g. DEL → MUM)"
        value={route}
        onChange={(e) => setRoute(e.target.value)}
      />

      <br /><br />

      <button onClick={addFlight}>
        Add Flight
      </button>

      <hr />

      {loggedIn ? (
        <button onClick={() => setLoggedIn(false)}>
          Logout
        </button>
      ) : (
        <button onClick={() => setLoggedIn(true)}>
          Login
        </button>
      )}

      <hr />

      {loggedIn ? (
        <UserPage flights={flights} />
      ) : (
        <GuestPage flights={flights} />
      )}
    </div>
  );
}