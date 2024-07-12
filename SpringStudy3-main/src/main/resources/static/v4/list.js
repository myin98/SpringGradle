import axios from 'axios'
export default {
	data() {
    	return {
			url1: "/v2/findList",
			url2: "/v4/detail",
			list: []
		}
    },
    created() {
		axios.post(this.url1).then(this.success).catch(this.error);
	},
	methods: {
		navEvent(accept) {
			axios.post((accept == undefined) ? this.url1 : `${this.url1}/${accept}`)
				.then(this.success).catch(this.error);
		},
		listEvent(row) {
			location.href = (row.no == undefined) ? this.url1 : `${this.url2}/${row.no}`;
		},
		success(res) { 
			this.list = (res.status == 200) ? res.data : [] 
		},
		error(err) { 
			console.log(err) 
		}
	}
}