export default function Animal({ title, numbers, src }) {
    return (
        <div className="flex flex-col self-center m-5">
            <img className="w-[5rem]" src={src} />
            <h1 className="card-title text-white self-center mt-2">{title}</h1>
            <h2 className="text-white self-center">{numbers}</h2>
        </div>
    );
}