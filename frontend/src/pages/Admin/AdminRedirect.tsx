import { useNavigate } from 'react-router-dom'

export default function AdminRedirect() {
    const navigate = useNavigate();

    navigate(`http:/localhost:8000/admin`);
}