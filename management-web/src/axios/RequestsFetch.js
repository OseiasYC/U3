import axios from "axios";

const requestsFetch = axios.create({
    baseURL: "http://localhost:8085/requests",
    headers: {
        "Content-Type": "application/json",
    },
});

export default requestsFetch;