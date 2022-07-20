import {useRef, useState} from "react";
import {Button} from "primereact/button";
import * as EmployeesModel from "../models/EmployeesModel";
import EmployeesTable from "./EmployeesTable";
import {InputNumber} from "primereact/inputnumber";
import {Toast} from "primereact/toast";


const UserHandleForm = () => {


    const [searching, setSearching] = useState(null);
    const [employees, setEmployees] = useState([]);
    const toast = useRef(null);

    const showInfo = () => {
        toast.current.show({severity: 'info', summary: 'Server is loading!', detail: 'Please, try again', life: 5000});
    }

    const showSuccess = () => {
        toast.current.show({severity: 'success', summary: 'Success', detail: 'record loaded', life: 3000});
    }

    const showWarn = () => {
        toast.current.show({
            severity: 'warn',
            summary: 'Not found!',
            detail: `The ID ${searching} has not been found`,
            life: 3000
        });
    }

    const getEmployee = async (id) => {
        let list = await EmployeesModel.get("http://localhost:8080/employees/" + id);
        if (list?.code == 404) {
            return 404;
        } else {
            if (list?.code != 429) {
                let array = [];
                array.push(list);
                setEmployees(array);
                return 200;
            } else {
                return null;
            }
        }
    }

    const getEmployees = async () => {
        let list = await EmployeesModel.get("http://localhost:8080/employees");
        if (list?.length > 0) {
            let array = [];
            array = list;
            setEmployees(array);
            return 200;
        } else {
            return null;
        }
    }

    const searchEmployee = async (id) => {
        let response;
        if (searching?.toString()?.length > 0) {
            response = await getEmployee(id);
        } else {
            response = await getEmployees();
        }

        if (response == 404) {
            showWarn();
        } else {
            if (response == 200) {
                showSuccess();
            } else {
                showInfo();
            }
        }
    }


    return (
        <>
            <InputNumber inputId="searching" value={searching} onValueChange={(e) => setSearching(e.value)}
                         mode="decimal" useGrouping={false} placeholder={"Search employee by ID ... "}
                         defaultValue={null}/>

            <Button icon="pi pi-search" className="p-button-rounded p-button-success" aria-label="Search"
                    onClick={() => searchEmployee(searching)}/>
            <Toast ref={toast}/>
            <EmployeesTable employees={employees}/>
        </>
    );

}

export default UserHandleForm;