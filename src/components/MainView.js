import 'primeicons/primeicons.css';
import 'primereact/resources/themes/lara-light-indigo/theme.css';
import 'primereact/resources/primereact.css';
import 'primeflex/primeflex.css';
import '../index.css';
import UserHandleForm from "./UserHandleForm";


const MainView = () => {

    return (
        <div>
            <div className="card">
                <UserHandleForm/>
            </div>
        </div>
    );
}

export default MainView;