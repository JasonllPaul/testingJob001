import {DataTable} from "primereact/datatable";
import {Column} from "primereact/column";


const EmployeesTable = (props) => {

    return (
        <div>
            <div className="card">
                <DataTable value={props.employees} header="List of Employees" responsiveLayout="stack"
                           breakpoint="960px">
                    <Column field="id" header="ID"/>
                    <Column field="employee_name" header="Name"/>
                    <Column field="employee_salary" header="Salary"/>
                    <Column field="employee_anual_salary" header="Annual Salary"/>
                    <Column field="employee_age" header="Age"/>
                    <Column field="profile_image" header="Profile image"/>
                </DataTable>
            </div>
        </div>
    );

}

export default EmployeesTable;