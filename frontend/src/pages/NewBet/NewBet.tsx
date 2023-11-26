import { useLocation, useParams, useNavigate } from "react-router-dom";
import { useMemo, useState } from "react";
import NewAnimalBet from "./NewAnimalBet";
import NewNumberBet from "./NewNumberBet";
import animals from "../../assets/animals.svg";
import numbers from "../../assets/numbers.svg";
import { requestWithToken } from '../../components/api.tsx';

function useQuery() {
    const { search } = useLocation();

    return useMemo(() => new URLSearchParams(search), [search]);
}

export default function NewBet() {
    let { id } = useParams();
    const type = useQuery().get("tipo");
    const navigate = useNavigate();
    const [inputValue, setInputValue] = useState(0);
    const [numbers, setNumbers] = useState([]);
    const [betType, setBetType] = useState("");
    const [errorMessage, setErrorMessage] = useState("");

    const bet = async () => {
        const requestData = {
            apostador: Number(localStorage.getItem('idApostador')),
            jogo: id,
            valor: inputValue,
            data: formatDate(new Date()),
            tipo: betType.toUpperCase(),
            numeros: numbers
        };

        try {
            const response = await requestWithToken(`http://localhost:8080/apostas`, navigate, 'POST', requestData);
            console.log(response);
            navigate('/');
        } catch (error) {
            setErrorMessage(JSON.parse(error.message).message);
        }
    }

    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setInputValue(Number(e.target.value));
    }

    function padTo2Digits(num) {
        return num.toString().padStart(2, '0');
      }
      
      function formatDate(date) {
        return (
          [
            date.getFullYear(),
            padTo2Digits(date.getMonth() + 1),
            padTo2Digits(date.getDate()),
          ].join('-') +
          ' ' +
          [
            padTo2Digits(date.getHours()),
            padTo2Digits(date.getMinutes()),
            padTo2Digits(date.getSeconds()),
          ].join(':')
        );
      }

    return (
        <div className="flex flex-col justify-center">
            <div className="flex self-center pb-6 pt-2">
                <img src={type === "1" ? animals : numbers} />
            </div>
            <div className="flex flex-wrap rounded border-4 border-secondary p-8 basis-[75%]">
                {type === "1" 
                    ? <NewAnimalBet betType={betType} setBetType={setBetType} numbers={numbers} setNumbers={setNumbers} /> 
                    : <NewNumberBet betType={betType} setBetType={setBetType} setNumbers={setNumbers} />}
                <div className="flex flex-col items-center min-w-full">
                    <span>R$</span>
                    <input className="input input-bordered bg-primary py-2 p-2 rounded-md" type="number" value={inputValue} onChange={handleInputChange} min="0"/>
                </div>
                <p className="text-red-500 text-xs italic">{errorMessage}</p>
                <div className="flex flex-col justify-end">
                    <button 
                        onClick={bet}
                        className={"btn btn-info glow-pink " + (inputValue <= 0 ? " btn-disabled" : "")}
                    >
                        Confirmar aposta
                    </button>
                </div>
            </div>
        </div>
    );
}