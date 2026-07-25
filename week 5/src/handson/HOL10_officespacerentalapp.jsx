import { useState } from "react";

export default function HOL10_officespacerentalapp() {
  const [office, setOffice] = useState([
    {
      name: "Cognizant Tech Park",
      rent: 75000,
      address: "Chennai",
      image: "https://placehold.co/160x100/10b981/0b0f19?text=Office",
    },
  ]);

  const [name, setName] = useState("");
  const [address, setAddress] = useState("");
  const [rent, setRent] = useState("");
  const [image, setImage] = useState("");

  const addOffice = () => {
    if (!name || !address || !rent) {
      alert("Please fill all required fields.");
      return;
    }

    setOffice([
      ...office,
      {
        name,
        address,
        rent: Number(rent),
        image:
          image ||
          "https://placehold.co/160x100/10b981/0b0f19?text=Office",
      },
    ]);

    setName("");
    setAddress("");
    setRent("");
    setImage("");
  };

  return (
    <div>
      <h2>Office Space Rental App</h2>

      <input
        type="text"
        placeholder="Office Name"
        value={name}
        onChange={(e) => setName(e.target.value)}
      />

      <br /><br />

      <input
        type="text"
        placeholder="Address"
        value={address}
        onChange={(e) => setAddress(e.target.value)}
      />

      <br /><br />

      <input
        type="number"
        placeholder="Rent"
        value={rent}
        onChange={(e) => setRent(e.target.value)}
      />

      <br /><br />

      <input
        type="text"
        placeholder="Image URL (optional)"
        value={image}
        onChange={(e) => setImage(e.target.value)}
      />

      <br /><br />

      <button onClick={addOffice}>Add Office</button>

      <hr />

      {office.map((item, index) => {
        const rentStyle = {
          color: item.rent <= 60000 ? "red" : "green",
          fontWeight: "bold",
        };

        return (
          <div
            key={index}
            style={{
              border: "1px solid gray",
              padding: "15px",
              marginBottom: "15px",
              borderRadius: "8px",
            }}
          >
            <img
              src={item.image}
              alt="Office"
              width="200"
              height="120"
            />

            <h3>{item.name}</h3>

            <p>Address: {item.address}</p>

            <p style={rentStyle}>
              Rent: ₹{item.rent.toLocaleString("en-IN")}
            </p>
          </div>
        );
      })}
    </div>
  );
}
