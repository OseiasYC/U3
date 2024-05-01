import axios from "axios";

const coursesFetch = axios.create({
    baseURL: "http://localhost:8081/courses",
    headers: {
        "Content-Type": "application/json",
    },
});

export default coursesFetch;