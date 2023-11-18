import { useLocation, useParams } from "react-router-dom";
import { useMemo } from "react";
import NewAnimalBet from "./NewAnimalBet";
import NewNumberBet from "./NewNumberBet";
import animals from "../../assets/animals.svg";
import numbers from "../../assets/numbers.svg";

function useQuery() {
    const { search } = useLocation();

    return useMemo(() => new URLSearchParams(search), [search]);
}

export default function NewBet() {
    let { id } = useParams();
    const type = useQuery().get("tipo");

    return (
        <div className="flex flex-col justify-center">
            <div className="flex self-center pb-6 pt-2">
                <img src={type === "1" ? animals : numbers} />
            </div>
            {type === "1" ? <NewAnimalBet id={id} /> : <NewNumberBet id={id} />}
        </div>
    );
}