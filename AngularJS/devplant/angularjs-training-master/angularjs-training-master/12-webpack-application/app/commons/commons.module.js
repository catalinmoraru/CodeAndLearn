import DataService from "./services/data.service";

export default angular.module('commonsModule', [])
    .service(DataService.$name, DataService).name;