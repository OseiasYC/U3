import axios from "axios";

const gradesFetch = axios.create({
    baseURL: "http://localhost:8083/grades",
    headers: {
        "Content-Type": "application/json",
    },
});

export default gradesFetch;