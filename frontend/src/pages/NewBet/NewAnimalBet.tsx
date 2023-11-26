import { useState, useEffect } from "react";
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
    { id: 1, title: "Avestruz", numbers: "01, 02, 03, 04", src: ostrich },
    { id: 2, title: "Águia", numbers: "05, 06, 07, 08", src: eagle },
    { id: 3, title: "Burro", numbers: "09, 10, 11, 12", src: donkey },
    { id: 4, title: "Borboleta", numbers: "13, 14, 15, 16", src: butterfly },
    { id: 5, title: "Cachorro", numbers: "16, 18, 19, 20", src: dog },
    { id: 6, title: "Cabra", numbers: "21, 22, 23, 24", src: goat },
    { id: 7, title: "Carneiro", numbers: "25, 26, 27, 28", src: ram },
    { id: 8, title: "Camelo", numbers: "29, 30, 31, 32", src: camel },
    { id: 9, title: "Cobra", numbers: "33, 34, 35, 36", src: snake },
    { id: 10, title: "Coelho", numbers: "37, 38, 39, 40", src: rabbit },
    { id: 11, title: "Cavalo", numbers: "41, 42, 43, 44", src: horse },
    { id: 12, title: "Elefante", numbers: "45, 46, 47, 48", src: elephant },
    { id: 13, title: "Galo", numbers: "49, 50, 51, 52", src: rooster },
    { id: 14, title: "Gato", numbers: "53, 54, 55, 56", src: cat },
    { id: 15, title: "Jacaré", numbers: "57, 58, 59, 60", src: aligator },
    { id: 16, title: "Leão", numbers: "61, 62, 63, 64", src: lion },
    { id: 17, title: "Macaco", numbers: "65, 66, 67, 68", src: monkey },
    { id: 18, title: "Porco", numbers: "69, 70, 71, 72", src: pig },
    { id: 19, title: "Pavão", numbers: "73, 74, 75, 76", src: peacock },
    { id: 20, title: "Peru", numbers: "77, 78, 79, 80", src: turkey },
    { id: 21, title: "Touro", numbers: "81, 82, 83, 84", src: bull },
    { id: 22, title: "Tigre", numbers: "85, 86, 87, 88", src: tiger },
    { id: 23, title: "Urso", numbers: "89, 90, 91, 92", src: bear },
    { id: 24, title: "Veado", numbers: "93, 94, 95, 96", src: deer },
    { id: 25, title: "Vaca", numbers: "97, 98, 99, 00", src: cow }
];

export default function NewAnimalBet({ betType, setBetType, numbers, setNumbers }) {
    const [selectedAnimals, setSelectedAnimals] = useState([]);
    
    useEffect(() => { 
        setBetType(handleTitle());
    }, [selectedAnimals]);

    const handleSelectedAnimals = (id, src) => {
        const newAnimals = [...selectedAnimals];
        const indexAnimals = newAnimals.indexOf(src);

        if (indexAnimals > -1) {
            newAnimals.splice(indexAnimals, 1);
        } else if (newAnimals.length < 5) {
            newAnimals.push(src);
        }

        setSelectedAnimals(newAnimals);

        const newNumbers = [...numbers];
        const indexNumbers = newNumbers.indexOf(id);

        if (indexNumbers > -1) {
            newNumbers.splice(indexNumbers, 1);
        } else if (newNumbers.length < 5) {
            newNumbers.push(id);
        }

        setNumbers(newNumbers);
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
            <Animal title={x.title} numbers={x.numbers} src={x.src} onClick={() => handleSelectedAnimals(x.id, x.src)} />)
    }

    return (
        <>
            {renderAnimals()}
            <div className="flex flex-col items-center min-w-full">
                <h1>Sua aposta:</h1>
                <ul className="flex">
                    {renderSelectedAnimals()}
                </ul>
                <h1 className="card-title text-white self-center mt-2">{betType}</h1>
            </div>
        </>
    );
}