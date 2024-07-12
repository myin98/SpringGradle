import axios from 'axios'
export default {
	data() {
    	return {
			url1: "/v2/save",
			url2: "/v2/findList",
			url3: "/v4/detail",
			map: {title: '', content: ''}
		}
    },
	methods: {
		formEvent() {
			axios.post(this.url1, null, {params: this.map}).then(this.success).catch(this.error);
		},
		success(res) { 
			location.href = (res.status == 200) ? `${this.url3}/${res.data.no}` : this.url2; 
		},
		error(err) { 
			console.log(err) 
		}
	}
}