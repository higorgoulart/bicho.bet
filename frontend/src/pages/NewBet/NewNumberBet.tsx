import { useState } from "react";

export default function NewNumberBet({ betType, setBetType, setNumbers }) {
    const [number, setNumber] = useState("");
    const [errorMessage, setErrorMessage] = useState("");

    const handleTitle = (value) => {
        switch (value.length) {
            case 4: return "Milhar";
            case 3: return "Centena";
            case 2: return "Dezena";
            default: return "";
        }
    }

    const isValid = (value) => {
        if (value === "")
            return "";

        if (isNaN(value)) {
            return "O campo deve ser um número válido.";
        }

        if (value.length < 2 || value.length > 4) {
            return "Insira um número de 10 a 9999.";
        }

        return "";
    }

    const handleNumber = (value) => {
        const error = isValid(value);

        setErrorMessage(error);
        setBetType(handleTitle(value));

        if (error !== "") {
            return;
        }

        setNumber(value);
        setNumbers([value])
    }

    return (
        <>
            <div className="flex flex-col items-center min-w-full">
                <h1 className="card-title text-white self-center mt-2">{betType}</h1>
                <input 
                    type="text" 
                    placeholder="Insira um número" 
                    className="input input-bordered w-full max-w-xs" 
                    onChange={(e) => handleNumber(e.target.value)}
                />
                <p className="text-red-500 text-xs italic">{errorMessage}</p>
            </div>
        </>
    );
}