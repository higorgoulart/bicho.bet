import { useParams } from "react-router-dom";

function Bet() {
    let { id } = useParams();

    return (
        <div className="flex justify-center mt-[5%]">
            <h1 className="card-title text-white">Aposta</h1>
        </div>
    );
}

export default Bet;