export default function Animal({ title, numbers, src, onClick }) {
    return (
        <div className="flex flex-col items-center m-5 w-[8.5rem]" onClick={() => onClick(src)}>
            <img className="object-cover" src={src} />
            <h1 className="card-title text-white self-center mt-2">{title}</h1>
            <h2 className="text-white self-center">{numbers}</h2>
        </div>
    );
}