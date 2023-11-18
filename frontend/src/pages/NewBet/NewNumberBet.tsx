import { useState } from "react";

export default function NewNumberBet({ id }) {
    const [number, setNumber] = useState("");
    const [betType, setBetType] = useState("");
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
    }

    // TODO: valor em uma próxima tela e request também
    const bet = async () => {
        const requestData = {
            apostador: 0,
            jogo: id,
            valor: 0,
            data: new Date(),
            tipo: 'NUMERO',
            numeros: 0
        };

        const requestOptions = {
            method: 'POST',
            body: JSON.stringify(requestData),
            headers: new Headers({
              'Content-Type': 'application/json',
              'Accept': 'application/json'
            }),
        };

        const response = await (await fetch(`http://localhost:8080/apostas/`, requestOptions)).json();

        console.log(response);
    }

    return (
        <div className="flex flex-wrap rounded border-4 border-secondary p-8 basis-[75%]">
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
            <div className="flex flex-col justify-end">
                <button 
                    className={"btn btn-info glow-pink " + (number === "" || errorMessage !== "" ? " btn-disabled" : "")}
                >
                    Confirmar aposta
                </button>
            </div>
        </div>
    );
}