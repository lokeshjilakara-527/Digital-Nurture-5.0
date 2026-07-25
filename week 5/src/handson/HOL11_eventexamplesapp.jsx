import { useState } from "react";

function Counter() {
  const [counter, setCounter] = useState(0);
  const [message, setMessage] = useState("");

  const sayHello = () => setMessage("Hello! ");

  const increment = () => {
    setCounter(counter + 1);
    sayHello();
  };

  const decrement = () => {
    setCounter(counter - 1);
  };

  const sayWelcome = (msg) => {
    alert(msg);
  };

  const handlePress = () => {
    alert("I was clicked");
  };

  return (
    <div>
      <h2>Counter Example</h2>

      <p>
        {message} Counter: {counter}
      </p>

      <button onClick={increment}>Increment</button>{" "}
      <button onClick={decrement}>Decrement</button>{" "}
      <button onClick={() => sayWelcome("Welcome to React!")}>
        Say Welcome
      </button>{" "}
      <button onClick={handlePress}>Press Me</button>
    </div>
  );
}

function CurrencyConvertor() {
  const [rupees, setRupees] = useState("");
  const [rate, setRate] = useState(90);
  const [euro, setEuro] = useState("");

  const convert = () => {
    if (!rupees || rate <= 0) {
      alert("Enter valid values");
      return;
    }

    setEuro((Number(rupees) / Number(rate)).toFixed(2));
  };

  return (
    <div>
      <h2>Currency Converter</h2>

      <label>Amount (₹): </label>
      <input
        type="number"
        value={rupees}
        onChange={(e) => setRupees(e.target.value)}
      />

      <br />
      <br />

      <label>Exchange Rate (1 EUR = ₹): </label>
      <input
        type="number"
        value={rate}
        onChange={(e) => setRate(e.target.value)}
      />

      <br />
      <br />

      <button onClick={convert}>Convert</button>

      {euro && (
        <h3>
          € {euro}
        </h3>
      )}
    </div>
  );
}

export default function HOL11_eventexamplesapp() {
  return (
    <div>
      <Counter />
      <hr />
      <CurrencyConvertor />
    </div>
  );
}