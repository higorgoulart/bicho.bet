import { useLocation, useParams } from "react-router-dom";
import { useMemo, useState, useEffect } from "react";
import Animal from "./Animal.tsx";
import ostrich from "../../assets/ostrich.svg";
import eagle from "../../assets/eagle.svg";
import donkey from "../../assets/donkey.svg";
import butterfly from "../../assets/butterfly.svg";
import dog from "../../assets/dog.svg";
import goat from "../../assets/goat.svg";
import ram from "../../assets/ram.svg";
import camel from "../../assets/camel.svg";
import snake from "../../assets/snake.svg";
import rabbit from "../../assets/rabbit.svg";
import horse from "../../assets/horse.svg";
import elephant from "../../assets/elephant.svg";
import rooster from "../../assets/rooster.svg";
import cat from "../../assets/cat.svg";
import aligator from "../../assets/aligator.svg";
import lion from "../../assets/lion.svg";
import monkey from "../../assets/monkey.svg";
import pig from "../../assets/pig.svg";
import peacock from "../../assets/peacock.svg";
import turkey from "../../assets/turkey.svg";
import bull from "../../assets/bull.svg";
import tiger from "../../assets/tiger.svg";
import bear from "../../assets/bear.svg";
import deer from "../../assets/deer.svg";
import cow from "../../assets/cow.svg";

const Animals = [
    { title: "Avestruz", numbers: "01, 02, 03, 04", src: ostrich },
    { title: "Águia", numbers: "05, 06, 07, 08", src: eagle },
    { title: "Burro", numbers: "09, 10, 11, 12", src: donkey },
    { title: "Borboleta", numbers: "13, 14, 15, 16", src: butterfly },
    { title: "Cachorro", numbers: "16, 18, 19, 20", src: dog },
    { title: "Cabra", numbers: "21, 22, 23, 24", src: goat },
    { title: "Carneiro", numbers: "25, 26, 27, 28", src: ram },
    { title: "Camelo", numbers: "29, 30, 31, 32", src: camel },
    { title: "Cobra", numbers: "33, 34, 35, 36", src: snake },
    { title: "Coelho", numbers: "37, 38, 39, 40", src: rabbit },
    { title: "Cavalo", numbers: "41, 42, 43, 44", src: horse },
    { title: "Elefante", numbers: "45, 46, 47, 48", src: elephant },
    { title: "Galo", numbers: "49, 50, 51, 52", src: rooster },
    { title: "Gato", numbers: "53, 54, 55, 56", src: cat },
    { title: "Jacaré", numbers: "57, 58, 59, 60", src: aligator },
    { title: "Leão", numbers: "61, 62, 63, 64", src: lion },
    { title: "Macaco", numbers: "65, 66, 67, 68", src: monkey },
    { title: "Porco", numbers: "69, 70, 71, 72", src: pig },
    { title: "Pavão", numbers: "73, 74, 75, 76", src: peacock },
    { title: "Peru", numbers: "77, 78, 79, 80", src: turkey },
    { title: "Touro", numbers: "81, 82, 83, 84", src: bull },
    { title: "Tigre", numbers: "85, 86, 87, 88", src: tiger },
    { title: "Urso", numbers: "89, 90, 91, 92", src: bear },
    { title: "Veado", numbers: "93, 94, 95, 96", src: deer },
    { title: "Vaca", numbers: "97, 98, 99, 00", src: cow }
];

function useQuery() {
    const { search } = useLocation();

    return useMemo(() => new URLSearchParams(search), [search]);
}

export default function NewBet() {
    let { id } = useParams();
    const type = useQuery().get("tipo");

    const [selectedAnimals, setSelectedAnimals] = useState([]);
    const [betType, setBetType] = useState("");
    
    useEffect(() => { 
        setBetType(handleTitle());
    }, [selectedAnimals]);

    const handleSelectedAnimals = (src) => {
        const newAnimals = [...selectedAnimals];
        const index = newAnimals.indexOf(src);

        if (index > -1) {
            newAnimals.splice(index, 1);
        } else if (newAnimals.length < 5) {
            newAnimals.push(src);
        }

        setSelectedAnimals(newAnimals);
    }

    const renderSelectedAnimals = () => {
        return selectedAnimals.map(x => <Animal title="" numbers="" src={x} />)
    }

    const handleTitle = () => {
        switch (selectedAnimals.length) {
            case 5: return "Quina";
            case 4: return "Quadra";
            case 3: return "Terno";
            case 2: return "Duque";
            case 1: return "Grupo";
            default: return "";
        }
    }

    const renderAnimals = () => {
        return Animals.map(x => 
            <Animal title={x.title} numbers={x.numbers} src={x.src} onClick={handleSelectedAnimals} />)
    }

    return (
        <div className="flex justify-center">
            {type === "1"
                ? (
                    <div className="flex flex-wrap rounded border-4 border-secondary p-8 basis-[75%]">
                        {renderAnimals()}
                        <div className="flex flex-col items-center min-w-full">
                            <h1>Sua aposta:</h1>
                            <ul className="flex">
                                {renderSelectedAnimals()}
                            </ul>
                            <h1 className="card-title text-white self-center mt-2">{betType}</h1>
                        </div>
                        <div className="flex flex-col justify-end">
                            <button className={"btn btn-info glow-pink " + (selectedAnimals.length < 1 ? " btn-disabled" : "")}>Confirmar aposta</button>
                        </div>
                    </div>
                )
                : (<div></div>)}
        </div>
    );
}